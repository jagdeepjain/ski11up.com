package services

import (
	"go-lang/go-serv/domain"
)

func GetContact(id int64) (*domain.Contact, error) {
	return domain.GetContact(id)
}
