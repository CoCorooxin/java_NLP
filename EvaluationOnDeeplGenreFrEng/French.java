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
