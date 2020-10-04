package domain

import (
	"errors"
	"fmt"
)

var contacts = map[int64]*Contact{
	1: {Id: 1, FirstName: "Jagdeep", LastName: "Jain"},
}

func GetContact(id int64) (*Contact, error) {
	contact := contacts[id]
	if contact != nil {
		return contact, nil
	}
	return nil, errors.New(fmt.Sprintf("User with id %v not found in our database...", id))
}
