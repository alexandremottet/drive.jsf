package fr.isima.drivejsf.controller;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import fr.isima.drivejsf.ejb.UserServiceEJB;
import fr.isima.drivejsf.entity.User;
import fr.isima.drivejsf.exception.NoDataFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

@ManagedBean
@SessionScoped
public class AuthController {
    private String code;
    private String email;
    private String displayName;
    private String accessToken;
    private AccessTokenResponse authResponse;
    private User authenticatedUser = null;

    private final String redirect_url = "http://localhost:8080/auth.xhtml";
    private final String CLIENT_ID = "951659947330-77kbd9kru1nq8r0fv22t2jngiace13gh.apps.googleusercontent.com";
    private final String CLIENT_SECRET = "MoJm8Ode6cNdMQq0yEBHNXcX";

    @EJB
    private UserServiceEJB userService;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        final String scope = "https://www.googleapis.com/auth/plus.profile.emails.read";

        //String authorizeUrl = new GoogleAuthorizationRequestUrl(CLIENT_ID, redirect_url, scope).build();

        String authorizeUrl = "https://accounts.google.com/o/oauth2/auth" +
                "?access_type=offline" +
                "&approval_prompt=force" +
                "&client_id=" + CLIENT_ID +
                "&redirect_uri=" + redirect_url +
                "&response_type=code" +
                "&scope=" + scope;

        return authorizeUrl;
    }

    public String getEmail() {
        return email;
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    public boolean isUserAuthenticated() {
        return (authenticatedUser != null);
    }

    public void onAuth () {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request =
                (HttpServletRequest) context.getExternalContext().getRequest();

        String error = request.getParameter("error");
        if (error != null) {
            System.out.println("Login failed : " + error);
            onLogout();
            return;
        }

        HttpTransport transport = new NetHttpTransport();
        JsonFactory json_factory = new JacksonFactory();

        try {

            // Exchange for an access and refresh token
            GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant authRequest =
                    new GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant(transport,
                            json_factory, CLIENT_ID, CLIENT_SECRET,
                            code, redirect_url);

            authRequest.useBasicAuthorization = false;
            authResponse = authRequest.execute();
            accessToken = authResponse.accessToken;

            // At this point we already have an access Token
            GoogleAccessProtectedResource access =
                    new GoogleAccessProtectedResource(accessToken,
                            transport, json_factory, CLIENT_ID, CLIENT_SECRET,
                            authResponse.refreshToken);
            HttpRequestFactory rf = transport.createRequestFactory(access);

            final String ep = "https://www.googleapis.com/plus/v1/people/me";

            GenericUrl gurl = new GenericUrl(ep);

            HttpRequest lreq = rf.buildGetRequest(gurl);

            HttpResponse lresp = lreq.execute();
            BufferedReader instream =
                    new BufferedReader(new InputStreamReader(lresp.getContent()));

            StringBuilder respData = new StringBuilder();

            String line;
            while ((line = instream.readLine()) != null) {
                respData.append(line);
            }

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(respData.toString());
            JSONObject jsonObject = (JSONObject) obj;
            displayName =  (String) jsonObject.get("displayName");

            JSONArray msg = (JSONArray) jsonObject.get("emails");
            Iterator<JSONObject> iterator = msg.iterator();
            JSONObject object = iterator.next();
            email = (String)object.get("value");

            authenticatedUser = userService.getUserWithEmail(email);
            if (authenticatedUser == null) {
                authenticatedUser = userService.createUser(email, displayName);
                System.out.println("New user created : " + authenticatedUser.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
            onLogout();
        }
    }

    public void onLogout() {
        email = "";
        displayName = "";
        authenticatedUser = null;
    }
}
