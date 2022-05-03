export interface IUiQueryform {
  id?: number;
  menuid?: number | null;
  code?: string | null;
  name?: string | null;
  ordernum?: number | null;
  issource?: boolean | null;
  requirement?: boolean | null;
  type?: string | null;
  placeholder?: string | null;
  config?: string | null;
}

export class UiQueryform implements IUiQueryform {
  constructor(
    public id?: number,
    public menuid?: number | null,
    public code?: string | null,
    public name?: string | null,
    public ordernum?: number | null,
    public issource?: boolean | null,
    public requirement?: boolean | null,
    public type?: string | null,
    public placeholder?: string | null,
    public config?: string | null
  ) {
    this.issource = this.issource ?? false;
    this.requirement = this.requirement ?? false;
  }
}
