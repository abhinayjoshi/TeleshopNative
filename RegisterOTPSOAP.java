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
 * Created by INDIA on 9/8/2016.
 */
public class RegisterOTPSOAP {
    private final String NAMESPACE = "http://tempuri.org/";
    private final String URL = "http://10.10.10.53/teleshop/7d9f50cf-78ec-4491-b2e1-b4088cbf189b.asmx?WSDL";
    private final String SOAP_ACTION = "http://tempuri.org/Registration";
    private final String METHOD_NAME = "Registration";

    public RegisterOTPSOAP() {
    }

    public String remotereq( String data) {


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Create the soap request object
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);



        Key k = new Key();
        String key = k.getKey();



        PropertyInfo prkey = new PropertyInfo();
        prkey.setName("R");
        prkey.setValue(key);
        prkey.setType(String.class);
        request.addProperty(prkey);


        PropertyInfo regType = new PropertyInfo();
        regType.setName("mobile");
        regType.setValue(data);
        regType.setType(String.class);
        request.addProperty(regType);





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
            Log.e("RatingsResponse", response.toString());

        } catch (Exception e) {

            Log.d("Error OTP", String.valueOf(e));
            e.printStackTrace();
        }
        return s.toString();

    }

}