package app

import (
	"fmt"
	"go-lang/go-serv/config"
	"go-lang/go-serv/controllers"
	"net/http"

	"github.com/gorilla/mux"
	"github.com/spf13/viper"
)

func StartApp() {

	viper.SetConfigName("config")
	viper.AddConfigPath(".")
	viper.AutomaticEnv()
	viper.SetConfigType("yml")

	r := mux.NewRouter()

	var configuration config.Configurations

	if err := viper.ReadInConfig(); err != nil {
		fmt.Printf("Error while reading config.yml %s", err)
	}

	err := viper.Unmarshal(&configuration)
	if err != nil {
		fmt.Printf("Error on unmarshal %v", err)
	}

	r.HandleFunc("/contact/{id:[0-9]+}", controllers.GetContact).Methods("GET")
	http.Handle("/", r)

	fmt.Println("app started...")
	err = http.ListenAndServe(configuration.Server.Port, nil)
	if err != nil {
		panic(err)
	}

}
