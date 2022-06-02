export interface ISlowSqlLogging {
  id?: number;
  traceId?: string | null;
  currentTime?: string | null;
  sql?: string | null;
  executeMillis?: string | null;
  executeParams?: string | null;
}

export class SlowSqlLogging implements ISlowSqlLogging {
  constructor(
    public id?: number,
    public traceId?: string | null,
    public currentTime?: string | null,
    public sql?: string | null,
    public executeMillis?: string | null,
    public executeParams?: string | null
  ) {}
}
