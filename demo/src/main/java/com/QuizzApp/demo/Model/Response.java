package com.QuizzApp.demo.Model;


public class Response {
    public Response(Integer id, String response) {
        this.id = id;
        this.response = response;
    }

    private Integer id;
    private String response;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
