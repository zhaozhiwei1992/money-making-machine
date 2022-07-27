export interface IRequestLogging {
  id?: number;
  traceId?: string | null;
  loginName?: string | null;
  requestURI?: string | null;
  clientIP?: string | null;
  currentTime?: string | null;
  requestName?: string | null;
  params?: string | null;
  success?: string | null;
}

export class RequestLogging implements IRequestLogging {
  constructor(
    public id?: number,
    public traceId?: string | null,
    public loginName?: string | null,
    public requestURI?: string | null,
    public clientIP?: string | null,
    public currentTime?: string | null,
    public requestName?: string | null,
    public params?: string | null,
    public success?: string | null
  ) {}
}
