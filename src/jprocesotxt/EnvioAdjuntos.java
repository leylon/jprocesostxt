
package jprocesotxt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.json.JSONObject;



/**
 *
 * Autor : Gchavez 
 * Fecha : 13.10.22
 * Comentario : Se realiza el consumo del servicio para enviar archivos adjuntos 
 */



public class EnvioAdjuntos {
    String jsonData="";
   private String env_bd;

    public String getEnv_bd() {
        return env_bd;
    }

    public void setEnv_bd(String env_bd) {
        this.env_bd = env_bd;
    }
  //  public List<HashMap<String,Object>> Envio(HttpServletRequest request) throws JsonMappingException,JsonProcessingExcepcion{
    //gchavez 04.07.23, Si se envio el adjunto retornar 1
    public Integer SendPost(String empresa,String numeroDoc,String puntoVenta,String serieDoc,String tipDoc)
    { 
        int li_return=0;
        try {
             ResourceBundle resource = ResourceBundle.getBundle("pe.plax.modelo.dao."+this.env_bd);
             String api_url = resource.getString("api.host");
            int code;
            
            //URL url = new URL("https://api.osf.pe/seres/subir_archivo");
            URL url = new URL(api_url);
            Map<String, Object> params=new LinkedHashMap<>();
            StringBuilder postData=new StringBuilder();
            for(Map.Entry<String,Object> param : params.entrySet())
            {
                if(postData.length()!=0)
                {
                    postData.append('&');
                } 
                postData.append(URLEncoder.encode(param.getKey(),"UTF-8"));
                postData.append("=");
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8")); 
            }
            /*Construir json*/
            JSONObject body=new JSONObject();
            body.put("empresa",empresa);
            body.put("numeroDoc",numeroDoc);
            body.put("puntoVenta",puntoVenta);
            body.put("serieDoc",serieDoc);
            body.put("tipoDoc",tipDoc);    
            
            byte[] postBytes=postData.toString().getBytes("UTF-8");
            
            /*Conexion al servicio y aplicar el tipo de aplicacion*/
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type","application/json");
            conn.setRequestProperty("Accept", "application/json");
            
            conn.setDoOutput(true);
            
            /*Envio del body*/
            try(OutputStream os = conn.getOutputStream()) {
            byte[] input = body.toString().getBytes("utf-8");
            os.write(input, 0, input.length);	}
            
            /*Respuesta del servicio*/
            
            code=conn.getResponseCode();
            if (code==200) 
            {
                System.out.print("Se enviaron los adjuntos correctamente. "); //gchavez 06.07.23
                li_return= 1;
            }
            else
            {
                li_return=0;
                System.out.print("Hubo un error interno al enviar los adjuntos");
            }
            /*
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));            
            for (int c=in.read();c!=-1;c=in.read())
            {
                System.out.print((char)c);
            }
            */

        
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
   


  
    return li_return;
   } 
    
}
