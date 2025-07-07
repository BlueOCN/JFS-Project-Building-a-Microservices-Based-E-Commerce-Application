package com.blueocn.ECommerceApplication.controller;

import com.blueocn.ECommerceApplication.service.IdEncodingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encoder")
public class IdEncoderController {

    private final IdEncodingService idEncoderService;

    public IdEncoderController(IdEncodingService idEncoderService) {
        this.idEncoderService = idEncoderService;
    }

    @GetMapping("encode/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Encode id")
    public String encode(@PathVariable(name = "id") Long id) {
        return idEncoderService.encode(id);
    }

    @GetMapping("decode/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Decode id")
    public Long decode(@PathVariable(name = "id") String id) {
        return idEncoderService.decode(id);
    }

}
