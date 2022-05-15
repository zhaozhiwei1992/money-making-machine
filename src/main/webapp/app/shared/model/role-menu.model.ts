export interface IRoleMenu {
  id?: number;
  roleId?: string | null;
  menuId?: string | null;
}

export class RoleMenu implements IRoleMenu {
  constructor(public id?: number, public roleId?: string | null, public menuId?: string | null) {}
}
