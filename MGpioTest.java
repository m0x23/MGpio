package MGpio;

/**
 *
 * @author markb
 */
public class MGpioTest 
{
    /**
     * Test class for MGpio
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try 
        {
            MGpio gpio17;
            gpio17 = new MGpio("17");    
            gpio17.setGpioDirection("out");
            while(true)
            {
                gpio17.setGpioValue("1");
                Thread.sleep(100);
                gpio17.setGpioValue("0");
                Thread.sleep(100);
            }
        }
        catch (InterruptedException ex) 
        {
            System.out.println("Main(): sleep error");
        }        
    }
}
