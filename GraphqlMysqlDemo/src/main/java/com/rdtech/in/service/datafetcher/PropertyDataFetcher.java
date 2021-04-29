package com.rdtech.in.service.datafetcher;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rdtech.in.model.Property;
import com.rdtech.in.repository.PropertyRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;



@Component
public class PropertyDataFetcher implements DataFetcher<List<Property>> {

  @Autowired
  private PropertyRepository propertyRepository;

  @Override
  public List<Property> get(DataFetchingEnvironment environment) {
    return propertyRepository.findAll();
  }
}
