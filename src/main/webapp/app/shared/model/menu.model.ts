export interface IMenu {
  id?: number;
  url?: string | null;
  path?: string | null;
  component?: string | null;
  name?: string | null;
  iconCls?: string | null;
  keepAlive?: number | null;
  requireAuth?: number | null;
  parentId?: number | null;
  enabled?: number | null;
}

export class Menu implements IMenu {
  constructor(
    public id?: number,
    public url?: string | null,
    public path?: string | null,
    public component?: string | null,
    public name?: string | null,
    public iconCls?: string | null,
    public keepAlive?: number | null,
    public requireAuth?: number | null,
    public parentId?: number | null,
    public enabled?: number | null
  ) {}
}
