package com.application.teleshopnative;

import android.os.StrictMode;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by INDIA on 9/5/2016.
 */
public class RegisterDataSOAP {
        private final String NAMESPACE = "http://tempuri.org/";
        private final String URL = "http://10.10.10.53/teleshop/7d9f50cf-78ec-4491-b2e1-b4088cbf189b.asmx?WSDL";
        private final String SOAP_ACTION = "http://tempuri.org/CustomerEntrySave";
        private final String METHOD_NAME = "CustomerEntrySave";

        public RegisterDataSOAP() {
        }

        public String remotereq(String name, String email, String mobile, String password, String otp) {


            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            // Create the soap request object
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


            Key k = new Key();
            String key = k.getKey();



            PropertyInfo prkey = new PropertyInfo();
            prkey.setName("CES");
            prkey.setValue(key);
            prkey.setType(String.class);
            request.addProperty(prkey);




            PropertyInfo pname = new PropertyInfo();
            pname.setName("name");
            pname.setValue(name);
            pname.setType(String.class);
            request.addProperty(pname);

            PropertyInfo p_email = new PropertyInfo();
            p_email.setName("email");
            p_email.setValue(email);
            p_email.setType(String.class);
            request.addProperty(p_email);

            PropertyInfo p_mobile = new PropertyInfo();
            p_mobile.setName("mobile");
            p_mobile.setValue(mobile);
            p_mobile.setType(String.class);
            request.addProperty(p_mobile);

            PropertyInfo p_pass = new PropertyInfo();
            p_pass.setName("Password");
            p_pass.setValue(password);
            p_pass.setType(String.class);
            request.addProperty(p_pass);

            PropertyInfo p_otp = new PropertyInfo();
            p_otp.setName("otp");
            p_otp.setValue(otp);
            p_otp.setType(String.class);
            request.addProperty(p_otp);





            // Create the envelop.Envelop will be used to send the request
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            // Says that the soap webservice is a .Net service
            envelope.dotNet = true;

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            String s = "";
            Double x = 1.0;
            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);

                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

                // Output to the log
                s = response.toString();
                Log.d("RatingsResponse", response.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return s.toString();

        }

}