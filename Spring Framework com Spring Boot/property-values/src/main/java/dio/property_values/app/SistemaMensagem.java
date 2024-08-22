package dio.property_values.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SistemaMensagem implements CommandLineRunner {
//    @Value("${name:NoReply-DIO}")
//    private String nome;
//    @Value("${email}")
//    private String email;
//    @Value("${telefones}")
//    private List<Long> telefones =
//            new ArrayList<>(Arrays.asList(new Long[]{11987654321L}));
    @Autowired
    private Remetente remetente;

    @Override
    public void run(String ... args) throws Exception{
        System.out.println("Mensagem enviada por: "
                +remetente.getNome()+"\nE-mail:"
                +remetente.getEmail() + "\n Com telefone para contato: "
                +remetente.getTelefones());
        System.out.println("Seu cadastro foi aprovado");
    }
}
