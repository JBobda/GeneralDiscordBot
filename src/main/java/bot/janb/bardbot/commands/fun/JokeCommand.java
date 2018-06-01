package bot.janb.bardbot.commands.fun;

import bot.janb.bardbot.Messages.MessageHandler;
import bot.janb.bardbot.ResourceManager;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.core.entities.MessageChannel;


@CommandInfo(
    name = {"Joke","Pun"},
    description = "Randomly chooses a joke from a list of jokes."
)
public class JokeCommand extends Command{

    private MessageChannel channel;
    private MessageHandler mHandler;
    private List<String> jokes;
    private ResourceManager resManager;
    
    public JokeCommand(){
        this.name = "joke";
        this.help = "Tells you a random joke";
        this.guildOnly = false;
        this.aliases = new String[]{"pun"};       
    }

    @Override
    protected void execute(CommandEvent event) {
        channel = event.getChannel();
        mHandler = new MessageHandler();
        
        resManager = new ResourceManager();
        try {
            jokes = resManager.loadTextFile("/jokes.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(JokeCommand.class.getName()).log(Level.SEVERE, null, ex);  
        }
        
        int choice = (int)(Math.random() * jokes.size());

        channel.sendMessage(mHandler.embedBuilder(name, jokes.get(choice)).build()).queue();
    }
    
}
