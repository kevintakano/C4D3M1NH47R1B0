package com.jkl.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
/**
 * Created by Aguiar on 12/07/2015.
 * Comentarios feitos nos grupos
 */

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Comment {

        @JsonProperty("id_grupo")
        private String id_grupo;

        @JsonProperty("id_usuario")
        private String id_usuario;

        @JsonProperty("mensagem")
        private String mensagem;

        @JsonProperty("data")
        private String data;

        public String getId_grupo() {
            return id_grupo;
        }

        public void setId_grupo(String id_grupo) {
            this.id_grupo = id_grupo;
        }

        public String getId_usuario() {
            return id_usuario;
        }

        public void setId_usuario(String id_usuario) {
            this.id_usuario = id_usuario;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

    }
