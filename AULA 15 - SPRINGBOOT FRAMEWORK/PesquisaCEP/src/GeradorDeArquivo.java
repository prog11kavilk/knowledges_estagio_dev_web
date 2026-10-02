import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class GeradorDeArquivo {
    public void geraJson(Endereco endereco) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter infos = new FileWriter(endereco.cep()+".json");
        infos.write(gson.toJson(endereco));
        infos.close();
    }
}
