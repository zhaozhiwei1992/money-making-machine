export interface ISysNotice {
  id?: number;
  title?: string | null;
  content?: string | null;
  creater?: string | null;
  createTime?: string | null;
  recType?: string | null;
  receiver?: string | null;
  urgent?: boolean | null;
  notiType?: number | null;
}

export class SysNotice implements ISysNotice {
  constructor(
    public id?: number,
    public title?: string | null,
    public content?: string | null,
    public creater?: string | null,
    public createTime?: string | null,
    public recType?: string | null,
    public receiver?: string | null,
    public urgent?: boolean | null,
    public notiType?: number | null
  ) {
    this.urgent = this.urgent ?? false;
  }
}
