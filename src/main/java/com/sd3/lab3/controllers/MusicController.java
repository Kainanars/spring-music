package com.sd3.lab3.controllers;

import com.sd3.lab3.dtos.MusicDto;
import com.sd3.lab3.entities.Music;
import com.sd3.lab3.services.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.persistence.PersistenceException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/musics")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @GetMapping
    @Operation(summary = "Obtém a lista de músicas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de músicas",
                            content = @Content(schema = @Schema(implementation = Music.class),
                                    examples = @ExampleObject(value = """
[
  {
    "duration": 3.54,
    "link": "https://www.youtube.com/watch?v=JGwWNGJdvx8",
    "title": "Shape of You",
    "genre": "Pop",
    "singer": "Ed Sheeran",
    "id": 352,
    "createdAt": "2024-03-18T14:02:44.413+00:00",
    "updatedAt": "2024-03-18T14:02:44.413+00:00"
  },
  {
    "duration": 8.02,
    "link": "https://www.youtube.com/watch?v=QkF3oxziUI4",
    "title": "Stairway to Heaven",
    "genre": "Rock",
    "singer": null,
    "id": 402,
    "createdAt": "2024-03-18T14:30:12.779+00:00",
    "updatedAt": "2024-03-18T14:30:12.779+00:00"
  }
]
                                            """))),
                    @ApiResponse(responseCode = "204", description = "Nenhuma música encontrada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            })
    public ResponseEntity<List<Music>> getAllMusic() {
        try {
            List<Music> musics = musicService.getAllMusic();
            if (musics.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(musics);
            }
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de acesso aos dados", e);
        } catch (PersistenceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de persistência", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Foi gerada uma exceção", e);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém uma música pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retorna a música encontrada",
                            content = @Content(schema = @Schema(implementation = Music.class),
                                    examples = @ExampleObject(value = """
                                              {
                                                "duration": 3.54,
                                                "link": "https://www.youtube.com/watch?v=JGwWNGJdvx8",
                                                "title": "Shape of You",
                                                "genre": "Pop",
                                                "singer": "Ed Sheeran",
                                                "id": 352,
                                                "createdAt": "2024-03-18T14:02:44.413+00:00",
                                                "updatedAt": "2024-03-18T14:02:44.413+00:00"
                                              },\
                                            """))),
                    @ApiResponse(responseCode = "404", description = "Música não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            })
    public ResponseEntity<Music> getMusicById(@PathVariable Long id) {
        try {
            Music music = musicService.getMusicById(id);
            if (music == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(music);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de acesso aos dados", e);
        } catch (PersistenceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de persistência", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Foi gerada uma exceção", e);
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Pesquisa músicas por título, cantor e gênero",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de músicas encontradas",
                            content = @Content(schema = @Schema(implementation = Music.class),
                            examples = @ExampleObject(value = """
                                    [
  {
    "duration": 5.55,
    "link": "https://www.youtube.com/watch?v=fJ9rUzIMcZQ",
    "title": "Bohemian Rhapsody",
    "genre": "Rock",
    "singer": null,
    "id": 403,
    "createdAt": "2024-03-18T14:30:18.858+00:00",
    "updatedAt": "2024-03-18T14:30:18.858+00:00"
  },
  {
    "duration": 6.3,
    "link": "https://www.youtube.com/watch?v=rfZlGzgCS9k",
    "title": "Hotel California",
    "genre": "Rock",
    "singer": null,
    "id": 404,
    "createdAt": "2024-03-18T14:30:25.023+00:00",
    "updatedAt": "2024-03-18T14:30:25.023+00:00"
  }
]
                                    """))),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            })
    public ResponseEntity<List<Music>> getMusics(@RequestParam(required = false) String title,
                                                 @RequestParam(required = false) String singer,
                                                 @RequestParam(required = false) String genre) {
        try {
            List<Music> musics = musicService.getMusics(title, singer, genre);
            return ResponseEntity.ok(musics);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de acesso aos dados", e);
        } catch (PersistenceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de persistência", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Foi gerada uma exceção", e);
        }
    }

    @PostMapping
    @Operation(summary = "Cria uma nova música",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MusicDto.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "title": "Sweet Child o' Mine",
                                      "singer": "Guns N' Roses",
                                      "genre": "Rock",
                                      "duration": 5.56,
                                      "link": "https://www.youtube.com/watch?v=1w7OgIMMRc4"
                                    }
                                    """)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Música criada com sucesso",
                            content = @Content(schema = @Schema(implementation = Music.class),
                                    examples = @ExampleObject(value = """
{
  "duration": 5.56,
  "link": "https://www.youtube.com/watch?v=1w7OgIMMRc4",
  "title": "Sweet Child o' Mine",
  "genre": "Rock",
  "singer": "Guns N' Roses",
  "id": 452,
  "createdAt": "2024-03-18T19:12:02.739+00:00",
  "updatedAt": "2024-03-18T19:12:02.739+00:00"
}
                                            """))),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            })
    public ResponseEntity<Music> createMusic(@org.springframework.web.bind.annotation.RequestBody MusicDto musicDto) {
        try {
            Music music = musicService.createMusic(musicDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(music);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de acesso aos dados", e);
        } catch (PersistenceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de persistência", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Foi gerada uma exceção", e);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma música existente",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MusicDto.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "title": "Sweet Child o' Mine",
                                      "singer": "Guns N' Roses",
                                      "genre": "Rock",
                                      "duration": 5.50,
                                      "link": "https://www.youtube.com/watch?v=1w7OgIMMRc4"
                                    }
                                    """)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Música atualizada com sucesso",
                            content = @Content(schema = @Schema(implementation = Music.class),
                                    examples = @ExampleObject(value = """
{
  "duration": 5.56,
  "link": "https://www.youtube.com/watch?v=1w7OgIMMRc4",
  "title": "Sweet Child o' Mine",
  "genre": "Rock",
  "singer": "Guns N' Roses",
  "id": 452,
  "createdAt": "2024-03-18T19:12:02.739+00:00",
  "updatedAt": "2024-03-18T19:12:02.739+00:00"
}
                                            """))),
                    @ApiResponse(responseCode = "404", description = "Música não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            })
    public ResponseEntity<Music> updateMusic(@PathVariable Long id, @org.springframework.web.bind.annotation.RequestBody MusicDto musicDto) {
        try {
            Music music = musicService.updateMusic(id, musicDto);
            if (music == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(music);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de acesso aos dados", e);
        } catch (PersistenceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de persistência", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Foi gerada uma exceção", e);
        }
    }


    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza parcialmente uma música existente",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                {
                                  "title": "Sweet Child o' Mine (Acoustic Version)",
                                  "genre": "Acoustic"
                                }
                                """)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Música atualizada parcialmente com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Música não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            })
    public ResponseEntity<Music> patchMusic(@PathVariable Long id, @org.springframework.web.bind.annotation.RequestBody Map<String, Object> updates) {
        try {
            Music music = musicService.patchMusic(id, updates);
            if (music == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(music);
        } catch (DataAccessException | PersistenceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro na atualização parcial da música", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Foi gerada uma exceção", e);
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma música existente",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Música excluída com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Música não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            })
    public ResponseEntity<Void> deleteMusic(@PathVariable Long id) {
        try {
            boolean deleted = musicService.deleteMusic(id);
            if (!deleted) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de acesso aos dados", e);
        } catch (PersistenceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de persistência", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Foi gerada uma exceção", e);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
