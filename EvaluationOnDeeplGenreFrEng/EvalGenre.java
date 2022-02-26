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
