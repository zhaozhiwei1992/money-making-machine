export interface ISystemParam {
  id?: number;
  code?: string | null;
  name?: string | null;
  value?: string | null;
  remark?: string | null;
  enable?: boolean | null;
}

export class SystemParam implements ISystemParam {
  constructor(
    public id?: number,
    public code?: string | null,
    public name?: string | null,
    public value?: string | null,
    public remark?: string | null,
    public enable?: boolean | null
  ) {
    this.enable = this.enable ?? false;
  }
}
