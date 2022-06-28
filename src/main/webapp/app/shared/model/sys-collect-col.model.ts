export interface ISysCollectCol {
  id?: number;
  colCname?: string | null;
  colEname?: string | null;
  tabId?: number | null;
  orderNum?: number | null;
  source?: string | null;
  isEdit?: boolean | null;
  requirement?: boolean | null;
  type?: string | null;
  config?: string | null;
}

export class SysCollectCol implements ISysCollectCol {
  constructor(
    public id?: number,
    public colCname?: string | null,
    public colEname?: string | null,
    public tabId?: number | null,
    public orderNum?: number | null,
    public source?: string | null,
    public isEdit?: boolean | null,
    public requirement?: boolean | null,
    public type?: string | null,
    public config?: string | null
  ) {
    this.isEdit = this.isEdit ?? false;
    this.requirement = this.requirement ?? false;
  }
}
