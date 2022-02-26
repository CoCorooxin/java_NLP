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
