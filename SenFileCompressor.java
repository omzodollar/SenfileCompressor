import archivage.Archivage;
import desarchivage.Desarchivage;
import java.lang.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SenFileCompressor{
  
    public static void main(String[] args){

        CharSequence cs1= "-c";
        CharSequence cs2= "-d";
        CharSequence cs3= "-h";
        CharSequence cs4= "-r";
        CharSequence cs5= "-v";
        Desarchivage desarchive=new Desarchivage();

        if(args.length < 1){

            System.out.println("Veuillez choisir une option svp");
        }
        
        else if(args[0].equals("-h"))
        {  

            for(int i=0; i<args.length; i++)

            if (args[i].contains(cs1)==true || args[i].contains(cs2)==true){
                System.out.println("Attention les options -c -d et -h sont mutuellement exclusives!!!");
                return;
            }
                       
            System.out.println("\n\t\t *****************************************  Aide du programme!!!  *****************************************");
            System.out.println("\n \t Cette partie  du programme est accessible en tapant  la commande suivante: ");
            System.out.println("\n \t java SenFileCompressor -h");
            System.out.println("\n\t\t *******************************  Archivage de fichiers!!!  *******************************");
            System.out.println("\n \t Ce programme permet l'archivage des fichiers c'est a dire l'utilisation d'un seul fichier pour stocker\n \t plusieurs fichiers et de pouvoir aussi a partir du fichier, regroupant le tout recuperer les fichiers d'origine");
            System.out.println("\n\t\t ******************************* Compression de fichiers!!!  *******************************");
            System.out.println("\n \t Il permet aussi de compresser des donnees qui consiste a diminuer l'espace occupe sur le support numerique sans perte de qualite ");
            System.out.println("\n \t Ainsi ce module du programme est acessible via la commande:  ");
            System.out.println("\n \t java SenFileCompressor -c <liste fichier a compresser> ");
            System.out.println("\n \t Cette commande donnera en sortie un fichier d'extension <<.sfc>> qui regroupe sous forme compressée les \n \t differents fichiers fournis en paramètre. ");
            System.out.println("\n \t Sur cette commande il est possible d'ajouter une option -r qui permet de specifier le chemin absolu ou relatif vers le repertoire");
            System.out.println("\n \t ou seront stockes les fichiers produits par le programme  ou bien l'option -f si le chemin n'existe pas auquel cas, le chemin \n \t sera automatiquement créé");
            System.out.println("\n \t Par exemple: Java SenFileCompressor –c fic1 fic2 fic3... [–r cheminVersRepertoire [–f] ] ");
            System.out.println("\n \t Il est possible aussi de pouvoir compresser vos donnees afin de reduire la taille du fichier d'archive en faisant de sorte que \n \t la taille du fichier regroupant le tout soit la plus petite possible");
            System.out.println("\n\t\t ****************************** Decompression de fichiers!!!  *******************************");
            System.out.println("\n \t Sur ce programme il est possible de decompresser un fichier existant en saisissant la commande suivante ");
            System.out.println("\n \t java SenFileCompressor -d <fichierADecompresser.sfc> ");
            System.out.println("\n \t Cette commande devra fournir en sortie l'integralite des fichiers contenus dans l’archive donné en paramètre ");
            System.out.println("\n \t Sur cette commande il est possible aussi  d'ajouter une option -r qui permet de specifier le chemin absolu ou relatif \n \t vers le repertoire");
            System.out.println("\n \t ou seront stockes les fichiers produits par le programme  ou bien l'option -f si le chemin n'existe pas auquel cas, le chemin \n \t sera automatiquement créé");
            System.out.println("\n \t Par exemple: Java SenFileCompressor –d fichier.sfc [–r cheminVersRepertoire [–f] ] ");
            System.out.println("\n\t\t ****************************** Verbosite du programme!!!  *******************************");
            System.out.println("\n \t L'option -v concerne la verbosite du programme qui donnera les details de l'execution du programme comme par exemple ");
            System.out.println("\n \t       Java SenFileCompressor –c fic1 fic2 fic3... [–r cheminVersRepertoire [–f] ] [–v]");
            System.out.println("\n \t –d fichier.sfc [–r cheminVersRepertoire [–f] ] [–v]");
            System.out.println("\n  ");        
            
        }

        else  if(args[0].equals("-c"))
        {

            for(int i=0; i<args.length; i++)
            if (args[i].contains(cs2)==true || args[i].contains(cs3)==true){
                System.out.println("Attention les options -c -d et -h sont mutuellement exclusives!!!");
                return;
            }

            for(int i=0; i<args.length; i++)

            if (args[i].contains(cs4)==true || args[i].contains(cs5)==true) {
                System.out.println("Compression de fichiers en specifiant le repertoire du fichier a compresser plus la verbosite si possible");
                return;
            }

            
            Archivage archive=new Archivage();
            String dirName = args[1];
            File dir = new File(dirName);
            archive.packDirectory(dir);            
        }

        else if(args[0].equals("-d"))
        {
             for(int i=0; i<args.length; i++)

                if (args[i].contains(cs1)==true || args[i].contains(cs3)==true){
                    System.out.println("Attention les options -c -d et -h sont mutuellement exclusives!!!");
                    return;
                }
           
                System.out.println("Integralite des fichiers contenus dans l'archive!!!");
                String fileName =args[1] ;
                String dirName =args[2] ;
                File fichieAdesarchiver = new File(fileName);
                desarchive.unPackInDirectory(fichieAdesarchiver,dirName);         
        }          
        
    }
}