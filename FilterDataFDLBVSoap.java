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
 * Created by INDIA on 9/16/2016.
 */
public class FilterDataFDLBVSoap {
    private final String NAMESPACE = "http://tempuri.org/";
    private final String URL = "http://10.10.10.53/teleshop/7d9f50cf-78ec-4491-b2e1-b4088cbf189b.asmx?WSDL";
    private final String SOAP_ACTION = "http://tempuri.org/FilterData_Subcat";
    private final String METHOD_NAME = "FilterData_Subcat";

    public FilterDataFDLBVSoap() {
    }

    public String remotereq(Integer id, String brand, String price, String discount, String sort) {


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Create the soap request object
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


        Key k = new Key();
        String key = k.getKey();


        PropertyInfo type = new PropertyInfo();
        type.setName("FDLB");
        type.setValue(key);
        type.setType(String.class);
        request.addProperty(type);


        PropertyInfo pID = new PropertyInfo();
        pID.setName("Id");
        pID.setValue(id);
        pID.setType(Integer.class);
        request.addProperty(pID);

        PropertyInfo b = new PropertyInfo();
        b.setName("Brands");
        b.setValue(brand);
        b.setType(String.class);
        request.addProperty(b);

        PropertyInfo p = new PropertyInfo();
        p.setName("PriceRange");
        p.setValue(price);
        p.setType(String.class);
        request.addProperty(p);

        PropertyInfo d = new PropertyInfo();
        d.setName("DiscountRange");
        d.setValue(discount);
        d.setType(String.class);
        request.addProperty(d);

        PropertyInfo so = new PropertyInfo();
        so.setName("SortCase");
        so.setValue(sort);
        so.setType(String.class);
        request.addProperty(so);


        PropertyInfo from = new PropertyInfo();
        from.setName("From");
        from.setValue(1);
        from.setType(Integer.class);
        request.addProperty(from);


        PropertyInfo to = new PropertyInfo();
        to.setName("To");
        to.setValue(400);
        to.setType(Integer.class);
        request.addProperty(to);




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
            Log.e("LSVBTBCP", response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s.toString();

    }

}