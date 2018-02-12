package desarchivage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Desarchivage {
	/** Writer en binaire */
	DataOutputStream dos ;
	
	/** Reader en binaire */
	DataInputStream dis;
	
	public void readFile(File input, File output) throws IOException
	{
		int fileCount = dis.readInt();
		System.out.println("L'archive contient "+  fileCount + " fichiers ");
		for(int i = 0 ; i < fileCount ; i++)
		{
			String fileName = dis.readUTF();
			long bytes = dis.readLong();
			System.out.println(" Unpacking : " + fileName +" - " + bytes/1024 + " kb ...");
			
			//On cr�e le fichier
			String filePath = output.getAbsolutePath()+"\\"+fileName;
			File outFile = new File(filePath);
			if(!outFile.exists())
			{
				outFile.createNewFile();
			}

			//on initialise le buffer d'ecriture dans le fichier out
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)));
			
			
			//ecriture du fichier
			final byte[] buffer = new byte[(int)bytes];
			int dataCount;
			dataCount = dis.read(buffer,0,(int)bytes);
			dos.write(buffer, 0, dataCount);
			
			
			
			//On ferme le flux d'ecriture
			dos.flush();
			dos.close();
		}
	}


	public void unPackInDirectory(File fichier,String directory) {
		//Commencer le packaging de fichier 
		if(fichier == null)
		{
			System.out.println("Dossier non choisi ! ");
			return;
		}
		
	
		//On cr�e un nouveau fichier de sortie
				File out = new File(directory);
				
				if(!out.exists())
				{
					out.mkdir();
		}
		//On initialise le flux de sortie pour ecrire dans notre fichier de compression
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fichier)));
		} catch (FileNotFoundException e1) {
			System.out.println("Impossible de lire dans le fichier source ! ");
			return;
		}
		
		//Si on peut ecrire dans le fichier, alors on d�marre le packaging 
		try {
			readFile(fichier,out);
		     System.out.println("desarchivage complet : ");

		} catch (IOException e2) {
			System.out.println("Erreur lors de la lecture du fichier");
		}
		
		// On ferme les flux proprement
		try {
			dis.close();
		} catch (IOException e1) {
			System.out.println("Erreur lors de la fermeture du flux d'ecriture ! ");
			e1.printStackTrace();
		}

	}
	
	
	 
	
}


