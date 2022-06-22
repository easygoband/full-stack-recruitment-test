declare namespace Express {
  interface Request {
    id: string;
    user: {
      _id: string,
    };
  }
}
