package uk.ac.ebi.pride.oauth.service;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uk.ac.ebi.pride.oauth.model.CustomUserDetails;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MongoClient mongoClient;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MongoDatabase database = mongoClient.getDatabase("pridelocal");
        MongoCollection<Document> collection = database.getCollection("pride_users");
        Document document = collection.find(Filters.eq("username",username)).first();
        if(document!=null) {
            String email = document.getString("email");
            String password = document.getString("password");
            //password = password!=null?"{bcrypt}"+password:password;
            List<String> authorities = new ArrayList<String>();
            authorities.add(document.getString("role"));

            CustomUserDetails userDetails = new CustomUserDetails(email,password,authorities.toArray(new String[authorities.size()]));
            return userDetails;
        }
        return null;
    }
}
