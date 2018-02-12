package archivage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Archivage {
	
	public void packDirectory(File directory)
	  {
	    
	    
	    //On initialise notre Writer et notre Reader à null
	    DataOutputStream writer = null;
	    DataInputStream reader = null;
	    
	    try{
				
				File nomFichier = new File("Archive.sfc");
				nomFichier.createNewFile();	
	      writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(nomFichier)));
	      
	      //On recupere les fichiers du dossier à archiver
	      File[] toPack = directory.listFiles(); 
	      
	      //On compte le nombre de fichiers uniquement
	      int fileCount = 0;
	      for(int i = 0 ; i < toPack.length ; i ++)
	      {
	        if(toPack[i].isFile())
	        {
	          fileCount ++;
	        }
	      }
	      writer.writeInt(fileCount); // on l'ecrit au début du fichier de sortie.
	      
	      //Ensuite on parcours chaque fichier à ecrire
	      for(File f : toPack)
	      {
	        if(f.isFile())
	        {
	          //On recupere le nom + la longueur du fichier
	          String name = f.getName();
	          long length = f.length();
	          
	          //On ecrit alors ces informations
	          writer.writeUTF(name);
	          writer.writeLong(length);
	          
	          //Puis on copie le contenu du fichier à la suite
	          reader = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
	          final byte[] buffer = new byte[1024]; //tampon 
	          int dataCount;
	          while((dataCount = reader.read(buffer, 0, buffer.length)) != -1 )
	          {
	            writer.write(buffer, 0, dataCount);
	          }
	          //On ferme le flux de lecture.
	          reader.close();
	        }  
	      }//Fin de parcours des fichiers
	      System.out.println("Archivage complet : ");

	      
	    }catch(IOException e){
	      System.out.println("Erreur lors de l'archivage du fichier : ");
	      e.printStackTrace();
	    }
	    finally
	    {
	      try {
	        if(writer != null)
	        {
	          writer.flush();
	          writer.close();
	        }
	        if(reader != null)
	        {
	          reader.close();
	        }
	        
	      } catch (IOException e) {
	        System.out.println("Impossible de fermer correctement les flux ");
	      }
	      
	    }
	        
	  }
	  
}
