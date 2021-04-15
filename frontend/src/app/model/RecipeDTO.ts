export class RecipeDTO {
    id: number;
    name: string;
    created: Date;
    category: boolean;
    servings: number;
    ingredients: string[] = [];
    instructions: string;
}