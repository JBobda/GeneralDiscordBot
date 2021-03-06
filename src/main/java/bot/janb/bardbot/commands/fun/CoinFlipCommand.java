package bot.janb.bardbot.commands.fun;

import bot.janb.bardbot.Messages.MessageHandler;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.core.entities.*;


@CommandInfo(
    name = "Coin",
    description = "Flips a virtual coin"
)
        
public class CoinFlipCommand extends Command{

    public MessageChannel channel;
    private MessageHandler mHandler;
    
    public CoinFlipCommand(){
        this.name = "coin";
        this.help = "Flips a virtual coin";
        this.guildOnly = false;
        this.aliases = new String[]{"flip"};
    }
    
    /**
     * Uses the Math.random() function to choose between
     * "Heads" or "Tails" and sends it to the channel of the event
     * 
     * @param event that contains the channel
     */
    @Override
    protected void execute(CommandEvent event) {
        //Finds channel to respond in
        channel = event.getChannel();
        mHandler = new MessageHandler();
        
        //Flips coin
        int choice = (int)(Math.random() * 2);
        String coin;
        if(choice == 1){
            coin = "Heads";
        }else{
            coin = "Tails";
        }

        channel.sendMessage(mHandler.embedBuilder(name, coin, event).build()).queue();
    }

}
