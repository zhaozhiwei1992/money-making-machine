export interface IExample {
  id?: number;
  name?: string | null;
  age?: number | null;
  sex?: string | null;
}

export class Example implements IExample {
  constructor(public id?: number, public name?: string | null, public age?: number | null, public sex?: string | null) {}
}
