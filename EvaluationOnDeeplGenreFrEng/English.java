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
