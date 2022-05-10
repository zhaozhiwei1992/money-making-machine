export interface IUiTab {
  id?: number;
  menuid?: number | null;
  code?: string | null;
  name?: string | null;
  ordernum?: number | null;
  config?: string | null;
}

export class UiTab implements IUiTab {
  constructor(
    public id?: number,
    public menuid?: number | null,
    public code?: string | null,
    public name?: string | null,
    public ordernum?: number | null,
    public config?: string | null
  ) {}
}
