package ru.kirilushkin.personalaccount.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.kirilushkin.personalaccount.entity.Account;

import java.io.IOException;

public class AccountDeserializer extends StdDeserializer<Account> {

    protected AccountDeserializer() {
        this(null);
    }

    protected AccountDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Account deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode resultNode = p.getCodec().readTree(p);
        String firstName = resultNode.get("firstName").asText();
        String lastName = resultNode.get("lastName").asText();
        String secondName = resultNode.get("secondName").asText();
        String photoUrl = resultNode.get("photoUrl").asText();
        Account account = Account.builder()
                .firstName(firstName)
                .lastName(lastName)
                .secondName(secondName)
                .photoUrl(photoUrl)
                .build();
        return account;
    }
}
