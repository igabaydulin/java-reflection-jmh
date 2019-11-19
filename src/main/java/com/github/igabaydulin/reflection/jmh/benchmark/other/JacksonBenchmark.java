package com.github.igabaydulin.reflection.jmh.benchmark.other;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class JacksonBenchmark {

  @State(Scope.Benchmark)
  public static class JacksonProvider {

    ObjectMapper objectMapper;
    String json;

    @Setup
    public void setUp() {
      objectMapper = new ObjectMapper();
      json = "{\"field\": \"Hello, world!\"}";
    }
  }

  @Benchmark
  public void jacksonMapping(JacksonProvider provider, Blackhole blackhole)
      throws JsonProcessingException {
    DTO dto = provider.objectMapper.readValue(provider.json, DTO.class);
    blackhole.consume(dto.getField());
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
