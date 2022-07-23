export interface ISysNoticeSub {
  id?: number;
  sysNoticeId?: number | null;
  recipientId?: string | null;
  updateTime?: string | null;
  status?: number | null;
}

export class SysNoticeSub implements ISysNoticeSub {
  constructor(
    public id?: number,
    public sysNoticeId?: number | null,
    public recipientId?: string | null,
    public updateTime?: string | null,
    public status?: number | null
  ) {}
}
