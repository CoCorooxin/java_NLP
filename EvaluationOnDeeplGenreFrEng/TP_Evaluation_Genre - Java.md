# TP_Evaluation_Genre - Java

## Xin Chen 2210582 L3_LI

I. 

1.

On peut déterminer le genre du groupe nominal par son contexte, notamment s' il y a une anaphore pronominale(he/she, his/her, him) qui fait référence à cette entité nominale. Par exemple dans la phrase “patron X has finished his job” le pronom “his” fait référence à “patron X” ce qui est un indice que le patron est un homme. Cette association anaphorique entre le nom et le pronom nous permet de prédire le genre du groupe nominal en français. 

 

2.

i. Dans un groupe nominal il y a notamment deux constituance qui peut exprimer le genre: [DET] [NOM]

*Cas a*: le genre est marqué sur le déterminant et sur le nom;

*Cas b*: le genre est seulement marqué sur le nom;

*Cas c* : le genre feminin n’est pas du tout marqué. Le genre du groupe nominal est toujours masculin. Remarques: en français il y a des noms de professionnels dont le genre feminin est très sous-représenté; en revanche dans certaines métiers, le genre maculin est rare(eg. Sage-femme vs Sage-homme)

*Cas e* : le genre n’est pas explicité à cause de la liaison; 

*Cas d*: le genre est seulement marqué sur le déterminant 

##### Les différentes manières dont le genre est exprimé en français est ici présenté en tableau: 

| genre    | **a**        | **b**       | **c**          | **d**     | **e**          |
| -------- | ------------ | ----------- | -------------- | --------- | -------------- |
| feminin  | la chanteuse | l’auditrice | ?              | l’artiste | la journaliste |
| masculin | le chanteur  | l’auditeur  | le charpentier | l’artiste | le journaliste |



ii. D’après l'analyse du genre en français la réponse à la première question est plus claire: dans la traduction du fr vers l’anglais on peut déterminer le genre du pronom associés à “work” en analysant le genre de l’antécédent à lequel le pronom fait référence. Et celà n’est pas toujours évident, par exemple dans le cas **d,** où le genre n’est pas du tout explicité. 



3.

Ce corpus est composé de phrases simples dont la structure est : [DET] + [NOM] + [PRED] + [POSS] + [NOM] . Cette structure syntaxique ne change pas lors de la traduction. 

Et en anglais, le porteur du marqueur en genre est sur [POSS]; en français c’est sur le 1er groupe nominal et le possessif. çàd la position du marqueur en genre est fixée, ce qui facilite l’analyse. 

Cette association est un à un, donc on peut modéliser le biais d’un système de traduction au moyen d’évaluer si cette association est correcte ou fautive, et calculer le taux d’erreur.

Par exemple pour une traduction de l’anglais vers le français:

| ANG vers FR | genre en anglais | genre en français | valeur |
| ----------- | ---------------- | ----------------- | ------ |
| phrase 1    | Masculin         | Masculin          | True   |
| phrase 2    | Feminin          | Masculin          | Fausse |



4.

i. Comme l’analyse sur le marqueur du genre en français on a fait ci dessus, un petit corpus avec très peu d'échantillons ne peut pas présenter cette variété en français. On a besoin d’un plus grand échantillon qu’on peut utiliser pour analyser toute sorte de situation.

ii.Dans le corpus, avec cette structure simple, on évite l'ambiguïté dans la structure syntaxique complexes lorsqu’il y a plusieurs antécédents dans le contexte. Par exemple dans la phrase “The patient made an appointment with the psychologist, but he canceled it shortly after because she had an emergency.“ Les antécédents et les anaphores pronominaux sont ambiguës.



II

\9. Si on découpe un problème en plusieurs sous-problèmes, ça nous permet de traîter ce problème d’une manière plus organisée et plus efficace, parce qu’il nous suffit de résoudre ces sous-problèmes un par un pour arriver au problème au départ. En plus, écrire des fonctions qui traitent un sous-problème à la fois, ça rend le code plus lisible donc lors du test, il est plus facile de détecter des erreurs.



  \13. à partir du résultat de l'évaluation, on observe qu'en général la traduction automatique a un taux d'erreur très élevé pour la prédiction du genre, soit du français vers l'anglais; soit de l'anglais vers le français.

Pour un ensemble de 388 phrases, la traduction automatique de l'anglais vers le français a traduit la plupart de noms feminin en français en masculin(142 sur 194 noms feminin).

La traduction automatique du français vers l'anglais a fait beaucoup d'erreur en traduisant la plupart de noms masculin en feminin(59 sur 191 noms feminin); pourtant, on peut aussi observer que la traduction automatique dans ce sens a plus de précision que l'autre(srcFr-toAng) probablement parce que la machine a du mal à déterminer la relation anaphorique entre le nom et le pronom qui le suit lors de l'analyse de la phrase source. En revanche, quand le marqueur de genre est porté sur le nom ou son déterminant, c'est plus facile à associer la valeur de genre avec le nom(c'est dans la même syntagme.) 

Quant aux noms épicènes, le système a toujours distingué entre masculin(16) et feminin(14) dans la traduction du français vers l’anglais parce que dans l’anglais il n’y a pas de mot épicène.



|                | srcFrench | srcEnglish |
| -------------- | --------- | ---------- |
| target French  | 388       | 208        |
| target English | 290       | 388        |

```java
import java.io.IOException;

public class EvalGenreDrive {

    public static void main(String[] args) throws IOException {
        //evaluation du genre de la traduction de l'anglais vers le français
        printEvaluationFromEngToFra();
        //evaluation du genre de la traduction du français ver l'anglais
        printEvaluationFromFraToEng();
    }
    //la traduction de l'anglais vers le français 208
    //{gold: feminin | pred: masculin=142, gold: masculin | pred: masculin=172, gold: feminin | pred: épicène=16, gold: masculin | pred: feminin=7, gold: feminin | pred: feminin=36, gold: masculin | pred: épicène=15}
    //la traduction du français vers l'anglais 290
    //{gold: feminin | pred: masculin=9, gold: masculin | pred: masculin=118, gold: masculin | pred: feminin=59, gold: feminin | pred: feminin=172, gold: épicène | pred: feminin=16, gold: épicène | pred: masculin=14}
    //le nombre de mots épicènes est 30

    public static void printEvaluationFromEngToFra() throws IOException {
        English srcEnglish = new English("probe_metier.gold.eng");
        French toFrench = new French("probe_metier.pred.deepl.fra");
        System.out.println(toFrench.parcoursCorpus());
        System.out.println("la traduction de l'anglais vers le français "+ toFrench.NomCorrectPred(srcEnglish.GoldGenre, toFrench.PredGenre));
        System.out.println(toFrench.confusionMatrix(srcEnglish.GoldGenre, toFrench.PredGenre));
    }

    public static void printEvaluationFromFraToEng() throws IOException {
        French srcFrench = new French("probe_metier.gold.fra");
        English toEnglish = new English("probe_metier.pred.deepl.eng");
        System.out.println("la traduction du français vers l'anglais " + toEnglish.NomCorrectPred(srcFrench.GoldGenre, toEnglish.PredGenre));
        System.out.println(toEnglish.confusionMatrix(srcFrench.GoldGenre, toEnglish.PredGenre));
        System.out.println("le nombre de mots épicènes est "+ srcFrench.cmptEpicene());
    }

}

```



```java
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;


public abstract class EvalGenre {

    public ArrayList<String> GoldGenre;
    public ArrayList<String> PredGenre;
    public String path;

    public EvalGenre(String path) throws IOException {
        this.GoldGenre = new ArrayList<>();
        this.PredGenre = new ArrayList<>();
        this.path = path;
    }

    public ArrayList<String> parcoursCorpus() throws IOException{
        ArrayList<String> readCorpus = new ArrayList<>();
        try (Stream<String> lines = Files.lines(
                Paths.get(path))){
            for (String line:
                    (Iterable<String>) lines::iterator){
                readCorpus.add(line);
            }
        }return readCorpus;
    }

    public ArrayList<String> genreList() throws IOException {
        ArrayList<String> genreList = new ArrayList<>();
        ArrayList<String> sentence = parcoursCorpus();
        for(String elem: sentence){
            genreList.add(DetectorGenre(elem));
        }return genreList;
    }

    public int NomCorrectPred(ArrayList<String> src, ArrayList<String> target) {
        int count = 0;
        for (int i = 0; i < src.size(); i++) {
            if (src.get(i) == target.get(i)) {
                count ++;
            }
        }
        return count;
    }

    public HashMap confusionMatrix(ArrayList<String> GenreGold, ArrayList<String> GenrePred) {
        HashMap<String, Integer> confusionMatrix = new HashMap<>();
        for(int i = 0; i < GenrePred.size() ; i++ ){
            String pred = GenrePred.get(i);
            String gold = GenreGold.get(i);
            String key = "gold: " + gold + " | " + "pred: " + pred ;
            confusionMatrix.put(key, confusionMatrix.getOrDefault(key, 0)+1);
        }
        return confusionMatrix;
    }

    abstract String DetectorGenre(String sentence);

}

```



```java
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


public class French extends EvalGenre{

    public French(String path) throws IOException {
        super(path);
        this.GoldGenre = this.genreList();
        this.PredGenre = this.genreList();
        this.path = String.valueOf(Paths.get(path).toAbsolutePath());
    }

    public ArrayList<String> listMotEpicene(){
        ArrayList<String> mot_epicene = new ArrayList<>();
        mot_epicene.add("l'athlète");
        mot_epicene.add("l'artiste");
        mot_epicene.add("l'astronome");
        mot_epicene.add("l'actuaire");
        mot_epicene.add("l'économiste");
        mot_epicene.add("l'hygiéniste");
        mot_epicene.add("l'archiviste");
        mot_epicene.add("l'écologiste");
        mot_epicene.add("l'interprète");
        mot_epicene.add("l'arbitre");
        mot_epicene.add("l'architecte");
        mot_epicene.add("l'élève");
        mot_epicene.add("l'artisan");
        mot_epicene.add("l'analyste");
        return mot_epicene;
    }

    public int cmptEpicene(){
        Integer count = 0 ;
        for(String genre : GoldGenre){
            if (genre == "épicène"){
                count++;
            }
        }return count;
    }

    public String DetectorGenre(String sentence){
        ArrayList<String> tmp = new ArrayList<>(Arrays.asList(sentence.split(" ")));
        ArrayList<String> mot_epicene = listMotEpicene();
        String todo = tmp.get(0);
        if(tmp.contains("la")){
                return "feminin";
            }else {
            if (tmp.contains("le")) {
                return "masculin";
            } else {
                if (todo.endsWith("teur") | todo.endsWith("er") | todo.endsWith("eur") | todo.endsWith("cien") | todo.endsWith("é") | todo.endsWith("t")) {
                    return "masculin";
                } else {
                    if (mot_epicene.contains(todo)) {
                        return "épicène";
                    } else {
                        return "feminin";
                    }
                }
            }
        }

    }

}

```



```java
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class English extends EvalGenre {

    public English(String path) throws IOException {
        super(path);
        this.GoldGenre = this.genreList();
        this.PredGenre = this.genreList();
        this.path = String.valueOf(Paths.get(path).toAbsolutePath());
    }

    public String DetectorGenre(String sentence){
        ArrayList<String> res = new ArrayList<String>(Arrays.asList(sentence.split(" ")));
        if (res.contains("his")){
            return "masculin";
        }else{
            return "feminin";
        }
    }

}

```

