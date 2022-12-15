import React from "react";
import { Container } from "react-bootstrap";
import NavBar from "../NavBar";
import { useLocalState } from "../util/useLocalStorage";

const Homepage = () => {
  const [jwt, setJwt] = useLocalState("", "jwt");
  return (
    <>
      <NavBar />
      <Container className="mt-5">
        <h1>Welcome Fellow Coders</h1>
        <p>the jwt is: {jwt}</p>
      </Container>
    </>
  );
};

export default Homepage;
