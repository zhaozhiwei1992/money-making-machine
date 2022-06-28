export interface ISysCollectTab {
  id?: number;
  tabCname?: string | null;
  tabEname?: string | null;
  config?: string | null;
  enable?: boolean | null;
}

export class SysCollectTab implements ISysCollectTab {
  constructor(
    public id?: number,
    public tabCname?: string | null,
    public tabEname?: string | null,
    public config?: string | null,
    public enable?: boolean | null
  ) {
    this.enable = this.enable ?? false;
  }
}
