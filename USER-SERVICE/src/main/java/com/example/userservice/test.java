package com.example.userservice;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;

import javax.ws.rs.client.Client;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><Contract>    <Header>        <Zone code=\"MTDISPO-50\" snfState=\"S\" value=\"+000000098300000\"/>        <Zone code=\"U8-EMPRUNTEUR\" snfState=\"S\" value=\"24554 M 24554SPECIMEN RAISON_SO\"/>        <Zone code=\"CDSITTPSIM\" snfState=\"S\" value=\"F\"/>        <Zone code=\"MTENCRSCC-50\" snfState=\"S\" value=\"+000000001700000\"/>        <Zone code=\"PRODUIT\" snfState=\"S\" value=\"LCADM - Ligne cautions Administr\"/>        <Zone code=\"RFEXT\" snfState=\"S\" value=\"AUTOCADM2-002\"/>        <Zone code=\"MTAUTO-80\" snfState=\"S\" value=\"+000000100000000\"/>        <Zone code=\"NODOSS\" snfState=\"S\" value=\"S-0001177\"/>    </Header>    <Data>        <Zone code=\"CPTENCRS-87\" snfState=\"C\" value=\"1234567890123477\"/>        <Zone code=\"NOMINAL-87\" snfState=\"C\" value=\"+000000001700000\"/>        <Zone code=\"CDSITTP\" snfState=\"C\" value=\"F\"/>        <Zone code=\"PROLIG\" snfState=\"C\" value=\"N\"/>        <Zone code=\"CDSITDOS-87\" snfState=\"C\" value=\"2\"/>        <Zone code=\"RFUTIL-87\" snfState=\"C\" value=\"00001682U\"/>        <Zone code=\"U8-LBSITIMP\" snfState=\"C\" value=\"Situation saine\"/>        <Zone code=\"UPDCDACTINT\" snfState=\" \" value=\"O\"/>        <Zone code=\"DTPROPO\" snfState=\"C\" value=\"20111121\"/>        <Zone code=\"CRDU-87\" snfState=\"C\" value=\"+000000001700000\"/>        <Zone code=\"ELTDOS-P\" snfState=\"C\" value=\"S-0001177           001\"/>        <Zone code=\"DTACCOR-87\" snfState=\"C\" value=\"20111121\"/>        <Zone code=\"DTDTC\" snfState=\" \" value=\"20111121\"/>        <Zone code=\"CDPDT-87\" snfState=\"C\" value=\"UCADM\"/>        <Zone code=\"NOUTIL\" snfState=\"C\" value=\"002\"/>        <Zone code=\"TYUTIL\" snfState=\"C\" value=\"G\"/>        <Zone code=\"DTEMIS-87\" snfState=\"C\" value=\"20111121\"/>        <Zone code=\"LBETAT\" snfState=\"C\" value=\"En cours de remboursement\"/>        <Zone code=\"TYCNT\" snfState=\"C\" value=\"L\"/>        <Zone code=\"ELTDOS\" snfState=\"C\" value=\"S-0001177           001002\"/>        <Zone code=\"SYNIPPECHI-86\" snfState=\"C\" value=\"000000000000000\"/>        <Zone code=\"CDDEVISE-87\" snfState=\"C\" value=\"MAD\"/>        <Zone code=\"INMONOTEC\" snfState=\"C\" value=\"O\"/>        <Zone code=\"INRACH\" snfState=\"C\" value=\"N\"/>        <Zone code=\"NOSLIGN\" snfState=\"C\" value=\"001\"/>        <Element source=\"DCAR\">            <Zone code=\"TYPE\" snfState=\"C\" value=\"D\"/>            <Zone code=\"NODOSS\" snfState=\"C\" value=\"S-0001177\"/>        </Element>        <Element source=\"DINTERV\">            <Zone code=\"RLTIERS-03\" snfState=\"C\" value=\"BENEF\"/>            <Zone code=\"RFTIERS-03\" snfState=\"C\" value=\"Nom du bénéficiaire 1\"/>        </Element>    </Data>    <Diagnostic returnCode=\"0\"/></Contract></body></html>";

    public static void main(String[] args) {
      String test=  xml.replaceAll("</body></html>", "");
        System.out.println(test);
// TODO Auto-generated method stub
        try {
            JSONObject json = XML.toJSONObject(test);
            Map<String, String > my_values=new HashMap<String, String>() ;
            for (Object a: json.getJSONObject("Contract").getJSONObject("Header").getJSONArray("Zone")) {
                JSONObject j=(JSONObject) a;
                System.out.println(j.getString("code") +" : " +j.get("value").toString());
                my_values.put(j.getString("code"),(String) j.get("value"));
            }
            for (Object a: json.getJSONObject("Contract").getJSONObject("Data").getJSONArray("Zone")) {
                JSONObject j=(JSONObject) a;
                System.out.println(j.getString("code") +" : " +j.get("value").toString());
                my_values.put(j.getString("code"), j.get("value").toString());
            }



        }catch (JSONException e) {
// TODO: handle exception
            System.out.println(e.toString());
        }

    }

}
