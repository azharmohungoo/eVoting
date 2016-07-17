package wsFunctions;

/**
 * Created by Andreas on 2016/07/17.
 */


import com.codex.evoting.multichain.Multichain;
import com.codex.evoting.database.Database;


import javax.annotation.PostConstruct;
import io.spring.guides.gs_producing_web_service.Login;
import io.spring.guides.gs_producing_web_service.SendVote;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class EVotingRepository {

    @PostConstruct
    public void initData() {
        //Here comes data if we wish to pre-initialize it before runtime.
    }

    public Login login(String username, String password) {
        Assert.notNull(username);
        Assert.notNull(password);

        Login result = new Login();

        Database database = new Database();

        result.setSuccess(database.login(username,password));

        return result;
    }


    public SendVote sendVote(String votingNode, String partyNode, String voteType){

        Multichain multichain = new Multichain();

        Assert.notNull(votingNode);
        Assert.notNull(partyNode);
        Assert.notNull(voteType);

        SendVote result = null;

        result = new SendVote();
        result.setSuccess(multichain.sendVote(votingNode, partyNode, voteType));

        return result;
    }
}