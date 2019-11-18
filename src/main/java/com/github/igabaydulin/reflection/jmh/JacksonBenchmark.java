package com.github.igabaydulin.reflection.jmh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class JacksonBenchmark {

  @State(Scope.Benchmark)
  public static class JacksonProvider {

    ObjectMapper objectMapper() {
      return new ObjectMapper();
    }
  }

  @Benchmark
  public void jacksonMapping(JacksonProvider provider, Blackhole blackhole)
      throws JsonProcessingException {
    ObjectMapper mapper = provider.objectMapper();
    DTO dto = mapper.readValue("{\"field\": \"Hello, world!\"}", DTO.class);
    blackhole.consume(dto);
  }

  public static class DTO {

    private String field;

    public String getField() {
      return field;
    }

    public void setField(String field) {
      this.field = field;
    }
  }
}
