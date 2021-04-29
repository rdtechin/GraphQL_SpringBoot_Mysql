package com.rdtech.in.service;

import java.io.File;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.rdtech.in.service.datafetcher.PropertyDataFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQlService {

  @Autowired
  private PropertyDataFetcher dataFetcherobj;


  @Value("classpath:graphql/property.graphql")
  Resource resource;
  private GraphQL graphQL;


  @PostConstruct
  private void loadSchema() throws IOException {

    File file = resource.getFile();

    // Get the graphql file
    TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
    RuntimeWiring runtimeWiring = buildRuntimeWiring();
    GraphQLSchema graphQLSchema
        = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  private RuntimeWiring buildRuntimeWiring() {
    return RuntimeWiring.newRuntimeWiring()
        .type("Query",
            typeWiring -> typeWiring
                .dataFetcher("allProperty", dataFetcherobj))
        .build();
  }

  public ExecutionResult executeGraphQL(String query) {
    return graphQL.execute(query);
  }

}