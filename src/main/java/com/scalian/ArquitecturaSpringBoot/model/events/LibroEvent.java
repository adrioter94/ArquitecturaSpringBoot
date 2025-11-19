package com.scalian.ArquitecturaSpringBoot.model.events;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroEvent {
    private Long libroId;
    private String status;
    private Instant timestamp;
}
