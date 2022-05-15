export interface IRoleMenuToolButton {
  id?: number;
  roleId?: string | null;
  menuId?: string | null;
  toolButtonId?: number | null;
}

export class RoleMenuToolButton implements IRoleMenuToolButton {
  constructor(public id?: number, public roleId?: string | null, public menuId?: string | null, public toolButtonId?: number | null) {}
}
