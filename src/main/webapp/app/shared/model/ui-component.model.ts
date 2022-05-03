export interface IUiComponent {
  id?: number;
  menuid?: number | null;
  ordernum?: number | null;
  componentid?: string | null;
  config?: string | null;
}

export class UiComponent implements IUiComponent {
  constructor(
    public id?: number,
    public menuid?: number | null,
    public ordernum?: number | null,
    public componentid?: string | null,
    public config?: string | null
  ) {}
}
