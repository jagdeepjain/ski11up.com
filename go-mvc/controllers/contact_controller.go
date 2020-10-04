package controllers

import (
	"encoding/json"
	"fmt"
	"go-lang/go-serv/services"
	"net/http"
	"strconv"

	"github.com/gorilla/mux"
)

func GetContact(resp http.ResponseWriter, req *http.Request) {
	id := mux.Vars(req)["id"]
	contactId, err := strconv.ParseInt(id, 10, 64)
	if err != nil {
		resp.WriteHeader(http.StatusBadRequest)
		resp.Write([]byte("id must be number...\n"))
		return
	}

	contact, err := services.GetContact(contactId)
	if err != nil {
		fmt.Printf("Got Error...\n")
		resp.WriteHeader(http.StatusNotFound)
		resp.Write([]byte(err.Error()))
		return
	}
	jsonVal, _ := json.Marshal(contact)
	resp.Write(jsonVal)
}
