package com.evoting;

//This is an enum for all Blockchain error messages. We only have to change these sentences for other wording.
public enum BlockchainErrorMessages {
    InvalidIP {
        public String toString() {
            return "The specified IP address is invalid.";
        }
    },
    InvalidPort {
        public String toString() {
            return "The specified port is invalid.";
        }
    },
    InvalidRPCCredentials {
        public String toString() {
            return "The specified RPC credentials is invalid.";
        }
    },
    InvalidVotesLeft {
        public String toString() {
            return "The sending Node doesn't have enough votes.";
        }
    },
    InvalidToAddress {
        public String toString() {
            return "The specified receiving Node address is invalid.";
        }
    },
    InvalidFromAddress {
        public String toString() {
            return "The specified sending Node address is invalid.";
        }
    },
    EmptyNodeRPCConfiguration {
        public String toString() {
            return "NodeIP or NodePort or RPC Username or RPC Password is empty.";
        }
    },
    NodeRequestBodyCannotBeEmpty {
        public String toString() {
            return "Internal error: Node request body cannot be empty.";
        }
    },
    Unknown {
        public String toString() {
            return "Internal error: An unkown error has occured.";
        }
    }
}