export interface IDataPermission {
  id?: number;
  code?: string | null;
  name?: string | null;
  ruleSql?: string | null;
}

export class DataPermission implements IDataPermission {
  constructor(public id?: number, public code?: string | null, public name?: string | null, public ruleSql?: string | null) {}
}
