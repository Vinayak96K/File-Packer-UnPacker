import java.lang.*;
import java.nio.file.*;
import java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.security.MessageDigest;
import java.io.*;

class FileMetaData implements Serializable
{
    public String FileName="";
    public long length=0;

    public FileMetaData(String FileName,long length)
    {
        this.FileName=FileName;
        this.length=length;
    }
}

class SerManager implements Serializable
{
    public String sChecksum;
    public ArrayList<FileMetaData> aObj;

    public SerManager()
    {
        sChecksum="";
        aObj=new ArrayList<FileMetaData>();
    }
}

class IOFileHelper
{
    private FileInputStream fin,fin2;
    private FileOutputStream fout1, fout2;
    private ObjectInputStream Oin;
    private ObjectOutputStream Oout;
    private static int iValue;
    private static final String ObjFileName = "/ObjFile.ser";
    private static final String DataFileName = "/DataFile.bin";

    public IOFileHelper()
    {

    }

    public String checkSumApacheCommons(String file) throws Exception
    {
        byte[] b = Files.readAllBytes(Paths.get(file));
        MessageDigest md=MessageDigest.getInstance("MD5");
        md.update(b);
        byte[] hash = md.digest();
        StringBuffer sb=new StringBuffer();
        for (byte b1  : hash)
        {
            sb.append(b1 & 0xff);
        }
        return sb.toString();
    }

    public int packFile(String sDirPath, String sDestPath) throws Exception
    {
        int iRet = 0;
        SerManager sObj=new SerManager();
        File fSrcPath= new File(sDirPath);
        FileFilter fileFilter=new FileFilter()
        {
            @Override
            public boolean accept(File arg0) {
                if((arg0.isFile()) && ( (arg0.getName().endsWith(".txt")) || (arg0.getName().endsWith(".c")) || (arg0.getName().endsWith(".cpp")) || (arg0.getName().endsWith(".java")) || (arg0.getName().endsWith(".xml")) || (arg0.getName().endsWith(".png")) ) )
                {
                    return true;
                }
                return false;
            }
        };
        File files[] = fSrcPath.listFiles(fileFilter);
        String sTemp[]= fSrcPath.getAbsolutePath().split("/");
        String sDestDireName= this.getValidDirName(sDestPath+"/Packed_"+sTemp[sTemp.length-1]);
        new File(sDestDireName).mkdir();
        String SerFile=sDestDireName + "/ObjFile.ser";
        String DataFile=sDestDireName + "/DataFile.bin";
        fout1 = new FileOutputStream(SerFile);
        fout2 = new FileOutputStream(DataFile,true);
        Oout = new ObjectOutputStream(fout1);
        for (File file : files)
        {
            System.out.println(file.getAbsolutePath());
            byte b[]=new byte[1024];
            FileMetaData fObj=new FileMetaData(file.getName(), file.length());
            sObj.aObj.add(fObj);
            fin=new FileInputStream(file);
            int len;
            while((len=(fin.read(b)))!=-1)
            {
                fout2.write(b,0,len);
            }
            iRet++;
            fin.close();
        }
        fout2.close();
        sObj.sChecksum=this.checkSumApacheCommons(DataFile);
        System.out.println(sObj.sChecksum);
        Oout.writeObject(sObj);
        Oout.close();
        fout1=null;
        fout2=null;
        Oout=null;
        return iRet;
    }

    public String getValidDirName(String str)
    {
        iValue=1;
        CharSequence sRet=str;
        while(new File(sRet.toString()).exists()==true)
        {
            System.out.println("Reach");
            sRet=str+""+iValue;
            iValue++;
        }
        return sRet.toString();
    }

    public int unPackFiles(String srcPath,String destPath) throws Exception
    {
        int iRet=0;
        String sPath[]=srcPath.split("/");
        if(sPath.length==0)
        {
            return -2;
        }
        fin=new FileInputStream(srcPath+IOFileHelper.ObjFileName);
        Oin =new ObjectInputStream(fin);
        fin2=new FileInputStream(srcPath+IOFileHelper.DataFileName);
        String check=this.checkSumApacheCommons(srcPath+"/DataFile.bin");
        SerManager sObj=(SerManager) Oin.readObject();
        System.out.println(sObj.sChecksum);
        
        System.out.println(check+"reach!!");
        if(check.compareTo(sObj.sChecksum)!=0)
        {
            return -2;
        }
        String sDestPath=this.getValidDirName(destPath + "/" + sPath[sPath.length-1].replace("Packed_",""));
        new File(sDestPath).mkdir();
        File DestFolder=new File(sDestPath);
        Iterator ir= sObj.aObj.iterator();
        while(ir.hasNext())
        {
            FileMetaData fObj=(FileMetaData)ir.next();
            fout2=new FileOutputStream(DestFolder.getAbsoluteFile()+"/"+fObj.FileName);
            long lSize= fObj.length;
            int iSize=1024;
            if(lSize <= 1024)
            {
                iSize = (int) lSize; 
            }
            int iLen=0;
            byte b[]=new byte[1024];
            while( (lSize > 0) && ((iLen=fin2.read(b,0,iSize))!=-1) )
            {
                fout2.write(b,0,iLen);
                lSize=lSize-iLen;
                if(lSize<=1024)
                {
                    iSize=(int)lSize;
                }
                else
                {
                    iSize=1024;
                }
            }
            iRet++;
            fout2.close();
        }

        return iRet;
    }
}