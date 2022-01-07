import java.util.List;
public class LandCreate {
    List<LandTransformer> transformers;

    LandCreate(List<LandTransformer> transformers) {
        this.transformers = transformers;
    }

    public void mix(Land land) {
        for (LandTransformer t : transformers) {
            t.execute(land);
        }
    }
}
