export interface IUiToolButton {
  id?: number;
  menuid?: number | null;
  code?: string | null;
  name?: string | null;
  ordernum?: number | null;
  action?: string | null;
  config?: string | null;
}

export class UiToolButton implements IUiToolButton {
  constructor(
    public id?: number,
    public menuid?: number | null,
    public code?: string | null,
    public name?: string | null,
    public ordernum?: number | null,
    public action?: string | null,
    public config?: string | null
  ) {}
}
